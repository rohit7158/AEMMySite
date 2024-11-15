package com.mysite.core.models;

import java.util.Date;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ArticleDetailsModel {
    
    @ValueMapValue(name = "articleTitle")
    @Default(values= "Article")
    private String title;

    @ValueMapValue(name = "articleDesc")
    private String description;

    @ValueMapValue
    private String articlePublisher;

    @ValueMapValue
    private Date articlePubDate;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getArticlePublisher() {
        return articlePublisher;
    }
    
    public Date getArticlePubDate() {
        return articlePubDate;
    }
    
}
