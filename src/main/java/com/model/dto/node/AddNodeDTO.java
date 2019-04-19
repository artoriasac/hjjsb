package com.model.dto.node;

import lombok.Data;

import java.util.List;

@Data
public class AddNodeDTO {
    private String mark;
    private List<Integer> dictionariesIds;
}
