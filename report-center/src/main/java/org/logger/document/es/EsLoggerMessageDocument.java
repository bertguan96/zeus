package org.logger.document.es;

import org.logger.document.LoggerMessageDocument;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "logger")
public class EsLoggerMessageDocument extends LoggerMessageDocument {
    @Id
    private String id;
    @Field(type = FieldType.Text)
    private String body;
    @Field(type = FieldType.Date)
    private String timestamp;
    @Field(type = FieldType.Text)
    private String threadName;
    @Field
    private String className;
    @Field
    private String level;
    @Field(type = FieldType.Text)
    private String exception;
    @Field(type = FieldType.Text)
    private String cause;

    public EsLoggerMessageDocument() {
    }

    public EsLoggerMessageDocument(String body, String timestamp, String threadName, String className,
                                   String level, String exception, String cause) {
        this.body = body;
        this.timestamp = timestamp;
        this.threadName = threadName;
        this.className = className;
        this.level = level;
        this.exception = exception;
        this.cause = cause;
    }

    @Override
    public String toString() {
        return "EsLoggerMessageDocument{" +
                "id='" + id + '\'' +
                ", body='" + body + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", threadName='" + threadName + '\'' +
                ", className='" + className + '\'' +
                ", level='" + level + '\'' +
                ", exception='" + exception + '\'' +
                ", cause='" + cause + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

}
