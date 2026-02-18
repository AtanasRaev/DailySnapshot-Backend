package com.dailysnapshotbackend.service.impl;

import com.dailysnapshotbackend.config.NewsRssProperties;
import com.dailysnapshotbackend.dto.NewsItemDTO;
import com.dailysnapshotbackend.service.NewsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class NewsServiceImpl implements NewsService {
    private final RestClient restClient;
    private final NewsRssProperties newsRssProperties;

    @Override
    public List<NewsItemDTO> search(String query, int limit) {
        int safeLimit = Math.clamp(limit, 1, 20);
        String uri = UriComponentsBuilder.fromUriString(this.newsRssProperties.getBaseUrl())
                .queryParam("q", query)
                .queryParam("hl", "en-US")
                .queryParam("gl", "US")
                .queryParam("ceid", "US:en")
                .toUriString();

        String xml = this.restClient.get()
                .uri(uri)
                .retrieve()
                .body(String.class);

        if (xml == null || xml.isBlank()) {
            throw new IllegalStateException("News RSS response is empty");
        }

        return parseItems(xml, safeLimit);
    }

    private List<NewsItemDTO> parseItems(String xml, int limit) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
            factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            factory.setXIncludeAware(false);
            factory.setExpandEntityReferences(false);

            Document document = factory.newDocumentBuilder().parse(new InputSource(new StringReader(xml)));
            NodeList items = document.getElementsByTagName("item");

            List<NewsItemDTO> result = new ArrayList<>();
            for (int i = 0; i < items.getLength() && result.size() < limit; i++) {
                Node node = items.item(i);
                if (!(node instanceof Element item)) {
                    continue;
                }

                result.add(new NewsItemDTO(
                        getChildText(item, "title"),
                        getChildText(item, "link"),
                        getChildText(item, "source"),
                        getChildText(item, "pubDate")
                ));
            }

            return result;
        } catch (Exception e) {
            throw new IllegalStateException("Failed to parse RSS news response", e);
        }
    }

    private String getChildText(Element parent, String tagName) {
        NodeList children = parent.getElementsByTagName(tagName);
        if (children.getLength() == 0) {
            return null;
        }
        String text = children.item(0).getTextContent();
        return text == null ? null : text.trim();
    }
}
