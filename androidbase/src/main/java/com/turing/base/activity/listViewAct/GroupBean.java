package com.turing.base.activity.listViewAct;

import java.util.List;

/**
 * MyApp
 *
 * @author Mr.Yang on 2016-01-28  14:53.
 * @version 1.0
 *          构造函数 + 属性
 */
public class GroupBean {

    private String groupName;
    private List<ChildBean> children;


    public GroupBean(String groupName, List<ChildBean> children) {
        this.groupName = groupName;
        this.children = children;
    }


    public GroupBean() {
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<ChildBean> getChildren() {
        return children;
    }

    public void setChildren(List<ChildBean> children) {
        this.children = children;
    }
}
