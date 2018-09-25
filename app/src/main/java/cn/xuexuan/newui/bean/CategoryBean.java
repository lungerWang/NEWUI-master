package cn.xuexuan.newui.bean;

import java.util.List;

/**
 * Created by Allen on 2018/9/25 0025.
 */

public class CategoryBean {

    private String name;
    private List<String> subCategories;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<String> subCategories) {
        this.subCategories = subCategories;
    }
}
