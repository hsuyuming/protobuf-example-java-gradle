package com.github.simpleabehsu.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import example.simple.Simple.SimpleMessage;

public class ProtoToJSONMain {
    public static void main(String[] args) {
        //Only Suggest on Debug purpose, not recommend use for Production

        SimpleMessage.Builder builder = SimpleMessage.newBuilder();

        // simple fields
        builder.setId(42)
                .setIsSimple(true)
                .setName("My Simple Message Name");

        // repeated field

        builder.addSampleList(1)
                .addSampleList(2)
                .addSampleList(3);

        try {
            String jsonString = JsonFormat
                    .printer()
//                    .includingDefaultValueFields()
                    .print(builder);
            System.out.println(jsonString);

            //parse JSON into Protobuf
            SimpleMessage.Builder builder2 = SimpleMessage.newBuilder();
            JsonFormat.parser()
                    .ignoringUnknownFields()
                    .merge(jsonString, builder2);
            System.out.println(builder2);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }
}
