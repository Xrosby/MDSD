'''
Created on 9 Jan 2018

@author: ups
'''

from dsl import *

system = entitysystem(
    entity.Person.
      field(String.name).
      field(Integer.age).
    entity.Course.
      field(String.title).
    entity.Student.extends.Person.
      field(Integer.id).
      relation.n_n.Course.follows(inverse.enrolled).
    entity.Teacher.extends.Person.
      relation.n_1.Course.teaches(inverse.taught_by)
    )
