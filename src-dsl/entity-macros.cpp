#define ENTITIES @Override public void build()
#define ENTITY(x) entity(#x).
#define FIELD(t,n) attribute(t .class,#n).
#define EXTENDS(x) sub(#x).
#define RELATION(kind,name,type,inverse) relation_##kind(#name,#type,#inverse). 
