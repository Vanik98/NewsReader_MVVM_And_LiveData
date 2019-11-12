package com.example.mvvmandlivedata.until;


import androidx.room.TypeConverter;



public class FieldsConverter {

    @TypeConverter
    public static String fromFields(Fields fields){
        return fields == null? null:fields.getThumbnail();
    }

    @TypeConverter
    public static Fields toFields(String thumbnail){
        return thumbnail == null? null:new Fields(thumbnail);

       }
}
