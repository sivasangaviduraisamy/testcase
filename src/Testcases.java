package com.example.memory;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import dev.langchain4j.memory.chat.ChatMemoryStore;
import dev.langchain4j.data.message.Message;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.SystemMessage;
import java.util.ArrayList;
import java.util.List;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Sorts.ascending;

public class MongoChatMemoryStore implements ChatMemoryStore {
    private final MongoCollection<Document> collection;
    private final String userId;     // Dynamic: unique per user
    private final String sessionId;  // Dynamic: per conversation/session

    public MongoChatMemoryStore(MongoClient mongoClient, String dbName, String collectionName, String userId, String sessionId) {
        this.collection = mongoClient.getDatabase(dbName).getCollection(collectionName);
        this.userId = userId;
        this.sessionId = sessionId;
    }

    @Override
    public void add(Object message) {
        String role;
        String content;
        if (message instanceof UserMessage userMessage) {
            role = "USER";
            content = userMessage.content();
        } else if (message instanceof AiMessage aiMessage) {
            role = "AI";
            content = aiMessage.content();
        } else if (message instanceof SystemMessage systemMessage) {
            role = "SYSTEM";
            content = systemMessage.content();
        } else {
            throw new IllegalArgumentException("Unsupported message type");
        }

        Document doc = new Document()
            .append("userId", userId)
            .append("sessionId", sessionId)
            .append("role", role)
            .append("content", content)
            .append("timestamp", System.currentTimeMillis());

        collection.insertOne(doc);
    }

    @Override
    public List<Object> getMessages() {
        List<Object> messages = new ArrayList<>();
        collection.find(and(eq("userId", userId), eq("sessionId", sessionId)))
            .sort(ascending("timestamp"))
            .forEach(doc -> {
                String role = doc.getString("role");
                String content = doc.getString("content");
                switch (role) {
                    case "USER" -> messages.add(UserMessage.from(content));
                    case "AI"   -> messages.add(AiMessage.from(content));
                    case "SYSTEM" -> messages.add(SystemMessage.from(content));
                }
            });
        return messages;
    }

    @Override
    public void clear() {
        collection.deleteMany(and(eq("userId", userId), eq("sessionId", sessionId)));
    }
}
