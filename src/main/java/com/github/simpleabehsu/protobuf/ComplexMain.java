package com.github.simpleabehsu.protobuf;

import example.complex.Complex.ComplexMessage;
import example.complex.Complex.DummyMessage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ComplexMain {

    public static void main(String[] args) {
        System.out.println("Complex example");

        DummyMessage oneDummy = newDummyMessage(55,"one dummy");

        ComplexMessage.Builder complexMessageBuilder = ComplexMessage.newBuilder();

        // signular message field
        complexMessageBuilder.setOneDummy(oneDummy);

        complexMessageBuilder.addMultipleDummy(newDummyMessage(66, "second dummy"))
                .addMultipleDummy(newDummyMessage(67, "second dummy"))
                .addMultipleDummy(newDummyMessage(68, "second dummy"))
                .addMultipleDummy(newDummyMessage(69, "second dummy"));

        complexMessageBuilder.addAllMultipleDummy(Arrays.asList(
                newDummyMessage(70, "other dummy"),
                newDummyMessage(71, "other second dummy")
        ));

         ComplexMessage message = complexMessageBuilder.build();
        System.out.println(message.toString());

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("complex_message.bin");
            message.writeTo(fileOutputStream);
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            System.out.println("Start reading complex message......");
            FileInputStream fileInputStream = new FileInputStream("complex_message.bin");
            ComplexMessage readFromFile = ComplexMessage.parseFrom(fileInputStream);
            fileInputStream.close();
            System.out.println(readFromFile.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Get message
        List<DummyMessage> dummyList =message.getMultipleDummyList();

        System.out.println("Start to iterate dummyList.....");
        dummyList.forEach(dummy -> System.out.println(dummy));



    }


    public static DummyMessage newDummyMessage(Integer id, String name){
        // same learning as "SimpleMain"
        DummyMessage.Builder dummyMessageBuilder = DummyMessage.newBuilder();
        DummyMessage message= dummyMessageBuilder.setName(name)
                .setId(id)
                .build();
        return message;

    }
}
