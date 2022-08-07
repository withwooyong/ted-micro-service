package com.ted.micro.recommend.persistence;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Document(collection = "recommend")
@CompoundIndex(name = "prod-rec-id", unique = true, def = "{'productId': 1, 'recommendId' : 1}")
public class RecommendEntity {

    @Id
    private String id;

    @Version
    private Integer version;

    private int productId;
    private int recommendId;
    private String author;
    private int rating;
    private String content;

    public RecommendEntity(int productId, int recommendId, String author, int rating, String content) {
        this.productId = productId;
        this.recommendId = recommendId;
        this.author = author;
        this.rating = rating;
        this.content = content;
    }
}
