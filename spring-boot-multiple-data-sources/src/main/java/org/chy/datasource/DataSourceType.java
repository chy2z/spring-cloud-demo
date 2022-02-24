package org.chy.datasource;

public enum DataSourceType {
    DEFAULT("masterDB", "主库读写"),
    V_MASTER("masterDB", "主库读写"),
    V_SLAVE("slaveDB", "从库读");


    private String name;
    private String desc;

    DataSourceType(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
