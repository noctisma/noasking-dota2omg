package com.noasking.opencv.pickimages;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by MaJing on 2018/1/19.
 */
@Data
public class PickImagePointDetail implements Serializable {

    private Point[][] hero = new Point[5][2];

    private Point[][] bigAbility = new Point[2][6];

    private Point[][] sampleAbility = new Point[6][6];

    public static void main(String[] args) throws JsonProcessingException {
        PickImagePointDetail pickImagePointDetail = new PickImagePointDetail();
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(pickImagePointDetail));
        System.out.println(98 * 0.10F);
    }

}
