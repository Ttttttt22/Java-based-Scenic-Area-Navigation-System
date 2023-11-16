package com.example.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Tan Shaobin
 * @Date: 2022/11/29/16:36
 * @Description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Spot {
    private String spid;
    private String spname;
    private String description;
}
