package com.usp.tabcloud.server.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2012-08-14 23:25:25")
/** */
public final class TabsInfoMeta extends org.slim3.datastore.ModelMeta<com.usp.tabcloud.shared.model.TabsInfo> {

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<com.usp.tabcloud.shared.model.TabsInfo, java.util.Date> date = new org.slim3.datastore.CoreAttributeMeta<com.usp.tabcloud.shared.model.TabsInfo, java.util.Date>(this, "date", "date", java.util.Date.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<com.usp.tabcloud.shared.model.TabsInfo, java.lang.Boolean> notified = new org.slim3.datastore.CoreAttributeMeta<com.usp.tabcloud.shared.model.TabsInfo, java.lang.Boolean>(this, "notified", "notified", boolean.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<com.usp.tabcloud.shared.model.TabsInfo, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<com.usp.tabcloud.shared.model.TabsInfo, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<com.usp.tabcloud.shared.model.TabsInfo> receiverEmail = new org.slim3.datastore.StringAttributeMeta<com.usp.tabcloud.shared.model.TabsInfo>(this, "receiverEmail", "receiverEmail");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<com.usp.tabcloud.shared.model.TabsInfo> requesterEmail = new org.slim3.datastore.StringAttributeMeta<com.usp.tabcloud.shared.model.TabsInfo>(this, "requesterEmail", "requesterEmail");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<com.usp.tabcloud.shared.model.TabsInfo> subjectText = new org.slim3.datastore.StringAttributeMeta<com.usp.tabcloud.shared.model.TabsInfo>(this, "subjectText", "subjectText");

    /** */
    public final org.slim3.datastore.StringCollectionAttributeMeta<com.usp.tabcloud.shared.model.TabsInfo, java.util.List<java.lang.String>> titles = new org.slim3.datastore.StringCollectionAttributeMeta<com.usp.tabcloud.shared.model.TabsInfo, java.util.List<java.lang.String>>(this, "titles", "titles", java.util.List.class);

    /** */
    public final org.slim3.datastore.StringCollectionAttributeMeta<com.usp.tabcloud.shared.model.TabsInfo, java.util.List<java.lang.String>> urls = new org.slim3.datastore.StringCollectionAttributeMeta<com.usp.tabcloud.shared.model.TabsInfo, java.util.List<java.lang.String>>(this, "urls", "urls", java.util.List.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<com.usp.tabcloud.shared.model.TabsInfo, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<com.usp.tabcloud.shared.model.TabsInfo, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    private static final TabsInfoMeta slim3_singleton = new TabsInfoMeta();

    /**
     * @return the singleton
     */
    public static TabsInfoMeta get() {
       return slim3_singleton;
    }

    /** */
    public TabsInfoMeta() {
        super("TabsInfo", com.usp.tabcloud.shared.model.TabsInfo.class);
    }

    @Override
    public com.usp.tabcloud.shared.model.TabsInfo entityToModel(com.google.appengine.api.datastore.Entity entity) {
        com.usp.tabcloud.shared.model.TabsInfo model = new com.usp.tabcloud.shared.model.TabsInfo();
        model.setDate((java.util.Date) entity.getProperty("date"));
        model.setNotified(booleanToPrimitiveBoolean((java.lang.Boolean) entity.getProperty("notified")));
        model.setKey(entity.getKey());
        model.setReceiverEmail((java.lang.String) entity.getProperty("receiverEmail"));
        model.setRequesterEmail((java.lang.String) entity.getProperty("requesterEmail"));
        model.setSubjectText((java.lang.String) entity.getProperty("subjectText"));
        model.setTitles(toList(java.lang.String.class, entity.getProperty("titles")));
        model.setUrls(toList(java.lang.String.class, entity.getProperty("urls")));
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        com.usp.tabcloud.shared.model.TabsInfo m = (com.usp.tabcloud.shared.model.TabsInfo) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("date", m.getDate());
        entity.setProperty("notified", m.isNotified());
        entity.setProperty("receiverEmail", m.getReceiverEmail());
        entity.setProperty("requesterEmail", m.getRequesterEmail());
        entity.setProperty("subjectText", m.getSubjectText());
        entity.setProperty("titles", m.getTitles());
        entity.setProperty("urls", m.getUrls());
        entity.setProperty("version", m.getVersion());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        com.usp.tabcloud.shared.model.TabsInfo m = (com.usp.tabcloud.shared.model.TabsInfo) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        com.usp.tabcloud.shared.model.TabsInfo m = (com.usp.tabcloud.shared.model.TabsInfo) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        com.usp.tabcloud.shared.model.TabsInfo m = (com.usp.tabcloud.shared.model.TabsInfo) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
    }

    @Override
    protected void incrementVersion(Object model) {
        com.usp.tabcloud.shared.model.TabsInfo m = (com.usp.tabcloud.shared.model.TabsInfo) model;
        long version = m.getVersion() != null ? m.getVersion().longValue() : 0L;
        m.setVersion(Long.valueOf(version + 1L));
    }

    @Override
    protected void prePut(Object model) {
    }

    @Override
    protected void postGet(Object model) {
    }

    @Override
    public String getSchemaVersionName() {
        return "slim3.schemaVersion";
    }

    @Override
    public String getClassHierarchyListName() {
        return "slim3.classHierarchyList";
    }

    @Override
    protected boolean isCipherProperty(String propertyName) {
        return false;
    }

    @Override
    protected void modelToJson(org.slim3.datastore.json.JsonWriter writer, java.lang.Object model, int maxDepth, int currentDepth) {
        com.usp.tabcloud.shared.model.TabsInfo m = (com.usp.tabcloud.shared.model.TabsInfo) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        if(m.getDate() != null){
            writer.setNextPropertyName("date");
            encoder0.encode(writer, m.getDate());
        }
        writer.setNextPropertyName("notified");
        encoder0.encode(writer, m.isNotified());
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder0.encode(writer, m.getKey());
        }
        if(m.getKeyAsString() != null){
            writer.setNextPropertyName("keyAsString");
            encoder0.encode(writer, m.getKeyAsString());
        }
        if(m.getReceiverEmail() != null){
            writer.setNextPropertyName("receiverEmail");
            encoder0.encode(writer, m.getReceiverEmail());
        }
        if(m.getRequesterEmail() != null){
            writer.setNextPropertyName("requesterEmail");
            encoder0.encode(writer, m.getRequesterEmail());
        }
        if(m.getSubjectText() != null){
            writer.setNextPropertyName("subjectText");
            encoder0.encode(writer, m.getSubjectText());
        }
        if(m.getTitles() != null){
            writer.setNextPropertyName("titles");
            writer.beginArray();
            for(java.lang.String v : m.getTitles()){
                encoder0.encode(writer, v);
            }
            writer.endArray();
        }
        if(m.getUrls() != null){
            writer.setNextPropertyName("urls");
            writer.beginArray();
            for(java.lang.String v : m.getUrls()){
                encoder0.encode(writer, v);
            }
            writer.endArray();
        }
        if(m.getVersion() != null){
            writer.setNextPropertyName("version");
            encoder0.encode(writer, m.getVersion());
        }
        writer.endObject();
    }

    @Override
    protected com.usp.tabcloud.shared.model.TabsInfo jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        com.usp.tabcloud.shared.model.TabsInfo m = new com.usp.tabcloud.shared.model.TabsInfo();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("date");
        m.setDate(decoder0.decode(reader, m.getDate()));
        reader = rootReader.newObjectReader("notified");
        m.setNotified(decoder0.decode(reader, m.isNotified()));
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("keyAsString");
        m.setKeyAsString(decoder0.decode(reader, m.getKeyAsString()));
        reader = rootReader.newObjectReader("receiverEmail");
        m.setReceiverEmail(decoder0.decode(reader, m.getReceiverEmail()));
        reader = rootReader.newObjectReader("requesterEmail");
        m.setRequesterEmail(decoder0.decode(reader, m.getRequesterEmail()));
        reader = rootReader.newObjectReader("subjectText");
        m.setSubjectText(decoder0.decode(reader, m.getSubjectText()));
        reader = rootReader.newObjectReader("titles");
        {
            java.util.ArrayList<java.lang.String> elements = new java.util.ArrayList<java.lang.String>();
            org.slim3.datastore.json.JsonArrayReader r = rootReader.newArrayReader("titles");
            if(r != null){
                reader = r;
                int n = r.length();
                for(int i = 0; i < n; i++){
                    r.setIndex(i);
                    java.lang.String v = decoder0.decode(reader, (java.lang.String)null)                    ;
                    if(v != null){
                        elements.add(v);
                    }
                }
                m.setTitles(elements);
            }
        }
        reader = rootReader.newObjectReader("urls");
        {
            java.util.ArrayList<java.lang.String> elements = new java.util.ArrayList<java.lang.String>();
            org.slim3.datastore.json.JsonArrayReader r = rootReader.newArrayReader("urls");
            if(r != null){
                reader = r;
                int n = r.length();
                for(int i = 0; i < n; i++){
                    r.setIndex(i);
                    java.lang.String v = decoder0.decode(reader, (java.lang.String)null)                    ;
                    if(v != null){
                        elements.add(v);
                    }
                }
                m.setUrls(elements);
            }
        }
        reader = rootReader.newObjectReader("version");
        m.setVersion(decoder0.decode(reader, m.getVersion()));
        return m;
    }
}