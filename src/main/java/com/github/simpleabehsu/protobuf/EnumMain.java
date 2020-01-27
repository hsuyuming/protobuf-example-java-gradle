package com.github.simpleabehsu.protobuf;

import example.enumerations.EnumExample;
import example.enumerations.EnumExample.EnumMessage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class EnumMain {

    public static void main(String[] args) {

        System.out.println("Example to Enums");
        EnumMessage.Builder builder = EnumMessage.newBuilder();

        builder.setId(345);
        /*
          safe way
          example with Enums
         */
        builder.setDayOfTheWeek(EnumExample.DayOfTheWeek.FRIDAY);

        EnumMessage message = builder.build();
        System.out.println(message.toString());

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("enums_message.bin");
            message.writeTo(fileOutputStream);
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            System.out.println("Read from protobuf binary file");
            FileInputStream fileInputStream = new FileInputStream("enums_message.bin");
            EnumMessage messageFromFile = EnumMessage.parseFrom(fileInputStream);
            fileInputStream.close();
            System.out.println(messageFromFile.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }




    }
}
