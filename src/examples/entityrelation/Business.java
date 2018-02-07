/*
Copyright (c) 2012, Ulrik Pagh Schultz, University of Southern Denmark
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met: 

1. Redistributions of source code must retain the above copyright notice, this
   list of conditions and the following disclaimer. 
2. Redistributions in binary form must reproduce the above copyright notice,
   this list of conditions and the following disclaimer in the documentation
   and/or other materials provided with the distribution. 

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

The views and conclusions contained in the software and documentation are those
of the authors and should not be interpreted as representing official policies, 
either expressed or implied, of the University of Southern Denmark.
*/

package examples.entityrelation;

import entityrelation.Entity;
import entityrelation.model.EntityRelationSystem;

/**
 * Entity-Relation example: small hierarchy of Student,Teacher extending Person and being related through Course
 * @author ups
 */
public class Business extends EntityRelationSystem {

    /*
     * Build concrete instance of semantic model
     * @see entityrelation.EntityRelation#build()
     */
	@Override
	protected void build() {
	    	entity("Order").
	    	  attribute(String.class,"product").
	    	  attribute(Integer.class,"price").
	    	entity("Contact").
	          attribute(String.class,"name").
	          attribute(String.class,"address").
	        entity("Customer").sub("Contact").
	          relation_n_1("orders","Order","bought_by").
	        entity("Business").sub("Contact").
	          relation_n_n("customers","Customer","shops_in").
	        entity("SingleCustomer").sub("Customer").
	          attribute(String.class,"date").
	        entity("ReturningCustomer").sub("Customer").
	          attribute(Integer.class,"count")
	        ;
	}
    /**
     * Use semantic model to create small system, query for data
     */
    void main() {
    	// Entity created and changes class
        Entity ulrik = new Entity(this, "Customer","Ulrik","Kolding",EMPTY,EMPTY);
        System.out.println("First: "+ulrik);
        ulrik.become("SingleCustomer","today");
        System.out.println("Then: "+ulrik);
        ulrik.become("ReturningCustomer",2);
        System.out.println("Last: "+ulrik);
        // Inverse automatically established
        new Entity(this, "Order","beer",20,ulrik);
        new Entity(this, "Order","chips",15,ulrik);
        System.out.println("Ulriks orders: "+ulrik.getMulti("orders"));
        // Shops and associations
        Entity brugsen = new Entity(this, "Business","DagligBrugsen","Kolding",EMPTY);
        Entity bob = new Entity(this, "SingleCustomer","Bob","Vejle",EMPTY,EMPTY,"January");
        ulrik.associate("shops_in", brugsen);
        bob.associate("shops_in", brugsen);
        new Entity(this, "Order","cake",30,bob);
        System.out.println("Ulriks shops: "+ulrik.getMulti("shops_in"));
        System.out.println("Customers in Brugsen buy: "+brugsen.getMulti("customers").getMulti("orders"));
    }

    /**
     * Run demo
     * @param argv
     */
    public static void main(String argv[]) {
        new Business().main();
    }

}
