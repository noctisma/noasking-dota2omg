package com.noasking.opencv.pickimages;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by MaJing on 2018/1/19.
 */
@Data
public class PickImageDetail implements Serializable {

    private int[][] hero = new int[5][2];

    private int[][] bigAbility = new int[2][6];

    private int[][] sampleAbility = new int[6][6];

    public static void main(String[] args) throws JsonProcessingException {
        PickImageDetail pickImagePointDetail = new PickImageDetail();
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(pickImagePointDetail));
        System.out.println(98 * 0.10F);
    }

}
