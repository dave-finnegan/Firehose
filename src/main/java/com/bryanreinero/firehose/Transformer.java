package com.bryanreinero.firehose;

import java.util.Map;
import java.util.HashMap;

import org.bson.types.ObjectId;

public abstract class Transformer <V extends Object> {

    public abstract V transform( String value );

    private static final Map<String, Transformer> transformers;

    private Transformer(){};
    
    public static final String TYPE_OBJECT_ID = "objectid";
    public static final String TYPE_STRING = "string";
    public static final String TYPE_INT = "int";
    public static final String TYPE_FLOAT = "float";
    public static final String TYPE_DOUBLE = "double";
    public static final String TYPE_SUBDOCUMENT = "subdocument";
    public static final String TYPE_ARRAY = "array";
    public static final String TYPE_BINARY = "binary";
    
    static {
        transformers = new HashMap<String, Transformer>();

        transformers.put( TYPE_OBJECT_ID,
            new Transformer <ObjectId> () {
                @Override
                public ObjectId transform( String value ) {
                    return new ObjectId( value );
                }
            }
        );
        
        transformers.put( TYPE_STRING ,
            new Transformer <String> () {
                @Override
                public String transform( String value ) {
                    return value ;
                }
            }
        );
        
        transformers.put( TYPE_INT ,
            new Transformer <Integer> () {
                @Override
                public Integer transform( String value ) {
                    return new Integer( value );
                }
            }
        );
        
        transformers.put(  TYPE_FLOAT ,
            new Transformer <Float> () {
                @Override
                public Float transform( String value ) {
                    return new Float( value );
                }
            }
        );
        
        transformers.put( TYPE_DOUBLE ,
            new Transformer <Double> () {
                @Override
                public Double transform( String value ) {
                    return new Double( value );
                }
            }
        );
        
        transformers.put( TYPE_SUBDOCUMENT ,
                new Transformer <Double> () {
                    @Override
                    public Double transform( String value ) {
                        return new Double( value );
                    }
                }
            );
    }

    public static Transformer getTransformer( String type ) {
        if( ! transformers.containsKey( type ) )
            throw new IllegalArgumentException( "Unsupported type: "+type);

        return transformers.get( type );
    }
}