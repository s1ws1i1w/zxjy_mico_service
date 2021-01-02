package com.bs.union.midware.base;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@ApiModel("分页查询请求对象")
public class PageRequestDTO {

    /**
     * 页码
     */
    @ApiModelProperty(value = "页码", name = "pageNum", required = true)
    protected Integer pageNum;

    /**
     * 每页记录数
     */
    @ApiModelProperty(value = "每页记录数", name = "pageSize", required = true)
    protected Integer pageSize;
}
