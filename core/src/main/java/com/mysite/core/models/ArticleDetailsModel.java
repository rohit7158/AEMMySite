package com.mysite.core.models;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.adobe.cq.export.json.ExporterConstants;

@Model(
	adaptables = {Resource.class, SlingHttpServletRequest.class},
	resourceType="mysite/components/article-detail",
	defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name=ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
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

    private boolean articleExpired;

    @PostConstruct
    public void init() {
        if(articlePubDate != null) {
            Date todayDate = new Date();
            if(articlePubDate.compareTo(todayDate) <= 0) {
                articleExpired = true;
            }
        }
    }

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

    public boolean isArticleExpired() {
        return articleExpired;
    }
    
    
}
