'''
Created on 9 Jan 2018

@author: ups
'''


class Field(object):
    def __init__(self, tipe, name):
        self.tipe = tipe
        self.name = name
    def dispatch(self,F):
        return F.field(self)

class Relation(object):
    def __init__(self, kind, tipe, name, inverse):
        self.kind = kind
        self.tipe = tipe
        self.name = name
        self.inverse = inverse
    def dispatch(self,F):
        return F.relation(self)

class Entity(object):
    def __init__(self, name):
        self.name = name
        self.extends = None
        self.properties = []
    def set_extends(self,extends):
        self.extends = extends
    def add_field(self,tipe,name):
        self.properties.append(Field(tipe,name))
    def add_relation(self,kind,tipe,name,inverse):
        self.properties.append(Relation(kind,tipe,name,inverse))
    def dispatch(self,F):
        return F.entity(self)

class SimplePrinter(object):
    def entity(self,e):
        print 'entity '+e.name
        if e.extends!=None:
            print'  extends '+e.extends
        for p in e.properties:
            p.dispatch(self)
    def field(self,f):
        print '  '+f.name+': '+f.tipe
    def relation(self,r):
        print '  '+r.name+': '+r.kind+' '+r.tipe+' inverse: '+r.inverse
