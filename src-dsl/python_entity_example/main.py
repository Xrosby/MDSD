'''
Created on 9 Jan 2018

@author: ups
'''

import example
import model

if __name__ == '__main__':
    printer = model.SimplePrinter()
    for e in example.university.entities.values():
        e.dispatch(printer)
