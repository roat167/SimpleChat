package com.orainteractive.simplechat.json.deserialize;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.orainteractive.simplechat.domain.Chat;

public class ChatListDeserializer extends StdDeserializer<List<Chat>>{	
	private static final long serialVersionUID = 1L;

	public ChatListDeserializer() {
        this(null);
    }
 
    public ChatListDeserializer(Class<?> vc) {
        super(vc);
    }
 
    @Override
    public List<Chat> deserialize(
      JsonParser jsonparser, 
      DeserializationContext context) 
      throws IOException, JsonProcessingException {
         
        return new ArrayList<>();
    }
}
