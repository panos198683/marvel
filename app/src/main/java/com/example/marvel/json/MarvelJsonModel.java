package com.example.marvel.json;

import java.util.Date;
import java.util.List;

public class MarvelJsonModel {
    private int id;
    private String name;
    private String description;
    private Date modified;
    private List<JsonthumbnailModel> thumbnail;
    private String recourceURI;
    private List<JsonComicModel> comics;
    private List<JsonSeriesModel> series;
    private List<JsonStoriesModel> stories;
    private List<JsonEventsModel> events;
    private List<JsonUrlsModel> urls;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public List<JsonthumbnailModel> getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(List<JsonthumbnailModel> thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getRecourceURI() {
        return recourceURI;
    }

    public void setRecourceURI(String recourceURI) {
        this.recourceURI = recourceURI;
    }

    public List<JsonComicModel> getComics() {
        return comics;
    }

    public void setComics(List<JsonComicModel> comics) {
        this.comics = comics;
    }

    public List<JsonSeriesModel> getSeries() {
        return series;
    }

    public void setSeries(List<JsonSeriesModel> series) {
        this.series = series;
    }

    public List<JsonStoriesModel> getStories() {
        return stories;
    }

    public void setStories(List<JsonStoriesModel> stories) {
        this.stories = stories;
    }

    public List<JsonEventsModel> getEvents() {
        return events;
    }

    public void setEvents(List<JsonEventsModel> events) {
        this.events = events;
    }

    public List<JsonUrlsModel> getUrls() {
        return urls;
    }

    public void setUrls(List<JsonUrlsModel> urls) {
        this.urls = urls;
    }

    public class JsonthumbnailModel {
        private String path;
        private String extension;

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getExtension() {
            return extension;
        }

        public void setExtension(String extension) {
            this.extension = extension;
        }
    }
    public class JsonComicModel {
        private int available;
        private String recourceURI;
        private List<ComicItems> items;
        private int returned;

        public int getAvailable() {
            return available;
        }

        public void setAvailable(int available) {
            this.available = available;
        }

        public String getRecourceURI() {
            return recourceURI;
        }

        public void setRecourceURI(String recourceURI) {
            this.recourceURI = recourceURI;
        }

        public List<ComicItems> getItems() {
            return items;
        }

        public void setItems(List<ComicItems> items) {
            this.items = items;
        }

        public int getReturned() {
            return returned;
        }

        public void setReturned(int returned) {
            this.returned = returned;
        }

        public class ComicItems{
            private String recourceURI;
            private String name;

            public String getRecourceURI() {
                return recourceURI;
            }

            public void setRecourceURI(String recourceURI) {
                this.recourceURI = recourceURI;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }

    public class JsonSeriesModel{
        private int available;
        private String collectionURI;
        private List<SeriesItems> items;
        private int returned;

        public int getAvailable() {
            return available;
        }

        public void setAvailable(int available) {
            this.available = available;
        }

        public String getCollectionURI() {
            return collectionURI;
        }

        public void setCollectionURI(String collectionURI) {
            this.collectionURI = collectionURI;
        }

        public List<SeriesItems> getItems() {
            return items;
        }

        public void setItems(List<SeriesItems> items) {
            this.items = items;
        }

        public int getReturned() {
            return returned;
        }

        public void setReturned(int returned) {
            this.returned = returned;
        }

        public class SeriesItems{
            private String recourceURI;
            private String name;

            public String getRecourceURI() {
                return recourceURI;
            }

            public void setRecourceURI(String recourceURI) {
                this.recourceURI = recourceURI;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }

    public class JsonStoriesModel{
        private int available;
        private String collectionURI;
        private List<StoriesItems> items;

        public int getAvailable() {
            return available;
        }

        public void setAvailable(int available) {
            this.available = available;
        }

        public String getCollectionURI() {
            return collectionURI;
        }

        public void setCollectionURI(String collectionURI) {
            this.collectionURI = collectionURI;
        }

        public List<StoriesItems> getItems() {
            return items;
        }

        public void setItems(List<StoriesItems> items) {
            this.items = items;
        }

        public class StoriesItems{
            private String resourceURI;
            private String name;
            private String type;

            public String getResourceURI() {
                return resourceURI;
            }

            public void setResourceURI(String resourceURI) {
                this.resourceURI = resourceURI;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }

    public class JsonEventsModel{
        private int available;
        private String collectionURI;
        private List<EventsItems> items;
        private int returned;

        public int getAvailable() {
            return available;
        }

        public void setAvailable(int available) {
            this.available = available;
        }

        public String getCollectionURI() {
            return collectionURI;
        }

        public void setCollectionURI(String collectionURI) {
            this.collectionURI = collectionURI;
        }

        public List<EventsItems> getItems() {
            return items;
        }

        public void setItems(List<EventsItems> items) {
            this.items = items;
        }

        public int getReturned() {
            return returned;
        }

        public void setReturned(int returned) {
            this.returned = returned;
        }

        public class EventsItems{
            private String resourceURI;
            private String name;

            public String getResourceURI() {
                return resourceURI;
            }

            public void setResourceURI(String resourceURI) {
                this.resourceURI = resourceURI;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }

    public class JsonUrlsModel{
        private String type;
        private String url;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
