'''
Created on 9 Jan 2018

@author: ups
'''

import university
import model

if __name__ == '__main__':
    printer = model.SimplePrinter()
    for e in university.system.entities.values():
        e.dispatch(printer)
