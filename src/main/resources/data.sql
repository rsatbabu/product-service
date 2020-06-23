

INSERT INTO product(id,name,price,instock,inorder,reorder,description)VALUES(1,'Gloves',50,12,0,100,'Quantity in 1 Box - 100 Gloves = 50 pairs in 1 box.');
INSERT INTO product(id,name,price,instock,inorder,reorder,description)VALUES(2,'Surgical Face Mask',40,100,10,100,'Ships from Canada - 100 Pack Disposable Face Safety, 3-Ply Ear Loop - Perfect for avoiding contagion in public areas.');
INSERT INTO product(id,name,price,instock,inorder,reorder,description)VALUES(3,'Hand Sanitizers',10,100,0,10,'Good hand hygiene kills transient microorganisms and removal of visible soil. Using an alcohol-based hand rub or soap and running water helps accomplish the task.');

    



INSERT INTO productmongo(id,detail)VALUES(1,'{"id":1,"name":"Gloves","description":"Quantity in 1 Box - 100 Gloves = 50 pairs in 1 box","price":50,"instock":100}');
INSERT INTO productmongo(id,detail)VALUES(2,'{"id":2,"name":"Surgical Face Mask","description":"Ships from Canada - 100 Pack Disposable Face Safety, 3-Ply Ear Loop - Perfect for avoiding contagion in public areas.","price":40,"instock":100}');
INSERT INTO productmongo(id,detail)VALUES(3,'{"id":3,"name":"Hand Sanitizers","description":"Good hand hygiene kills transient microorganisms and removal of visible soil. Using an alcohol-based hand rub or soap and running water helps accomplish the task.","price":10,"instock":100}');

INSERT INTO customer(customer_id, email,phone)VALUES('john','test@email.com','514-111-1111');
INSERT INTO customer(customer_id, email,phone)VALUES('jack','test@email.com','514-111-2222'); 
