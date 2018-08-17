package model;

public class Document {

    private int id;
    private String content;
    private String name;
    private String alias;

    public Document( String name, String cont, String alias) {
        //this.id = id;
        this.name = name;
        this.content = cont;
        this.alias = alias;

    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }


    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
