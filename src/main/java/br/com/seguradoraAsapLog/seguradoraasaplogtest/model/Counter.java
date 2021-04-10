package br.com.seguradoraAsapLog.seguradoraasaplogtest.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "counters")
public class Counter {

    @Id
    private String id;
    private int seq;

    public Counter(String id, int seq) {
        this.id = id;
        this.seq = seq;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }
}