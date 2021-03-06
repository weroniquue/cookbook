DROP procedure IF EXISTS ingredientsProcedure;
create procedure ingredientsProcedure()
select * from ingredients;

DROP FUNCTION IF EXISTS portion;

CREATE FUNCTION portion(amount float(11,2),ratio float(11,2))
RETURNS float(11,2)
READS SQL DATA
DETERMINISTIC
BEGIN
    DECLARE newAmount float(11,2);
    SET newAmount = amount*ratio;
    RETURN newAmount;
END;

DROP procedure IF EXISTS commentsProcedure;
create procedure commentsProcedure()
select * from comments;


CREATE TABLE IF NOT EXISTS amountingredients (
    amount             float(10,2) NOT NULL,
    ingredients_name   VARCHAR(30) NOT NULL,
    recipes_id         INTEGER NOT NULL
);

CREATE TABLE category (
    name   VARCHAR(30) NOT NULL
);

ALTER TABLE category ADD CONSTRAINT category_pk PRIMARY KEY ( name );

select * from recipesinrestaurant;

ALTER TABLE comments MODIFY COLUMN id INTEGER NOT NULL auto_increment;

CREATE TABLE comments (
    id             INTEGER NOT NULL auto_increment,
    comment        VARCHAR(100) NOT NULL,
    date           DATE NOT NULL,
    users_username   VARCHAR(20) NOT NULL,
    recipes_id       INTEGER NOT NULL,
    CONSTRAINT comments_pk PRIMARY KEY ( id )
    
);


CREATE TABLE cuisine (
    name   VARCHAR(30) NOT NULL
);

ALTER TABLE cuisine ADD CONSTRAINT cuisine_pk PRIMARY KEY ( name );

CREATE TABLE ingredients (
    name   VARCHAR(30) NOT NULL,
    unit   VARCHAR(15)
);

ALTER TABLE ingredients ADD CONSTRAINT ingredients_pk PRIMARY KEY ( name );

CREATE TABLE photos (
    path         VARCHAR(50) NOT NULL,
    recipes_id   INTEGER NOT NULL
);

ALTER TABLE photos ADD CONSTRAINT photos_pk PRIMARY KEY ( path );


CREATE TABLE recipes (
    id               INTEGER NOT NULL auto_increment,
    tittle           VARCHAR(100) NOT NULL,
    description      VARCHAR(300) NOT NULL,
    cuisine_name     VARCHAR(30) NOT NULL,
    users_username   VARCHAR(20) NOT NULL,
    category_name    VARCHAR(30),
    CONSTRAINT recipes_pk PRIMARY KEY ( id )
);


CREATE TABLE recipesinrestaurant (
    restaurants_name   VARCHAR(40) NOT NULL,
    restaurants_city   VARCHAR(40) NOT NULL,
    recipes_id         INTEGER NOT NULL
);


CREATE TABLE restaurants (
    name      VARCHAR(40) NOT NULL,
    city      VARCHAR(40) NOT NULL,
    address   VARCHAR(50),
    code      VARCHAR(10)
);

ALTER TABLE restaurants ADD CONSTRAINT restaurants_pk PRIMARY KEY ( name,
                                                                    city );

CREATE TABLE steps (
    number      INTEGER NOT NULL,
    description   VARCHAR(80) NOT NULL,
    recipes_id    INTEGER NOT NULL
);

CREATE TABLE users (
    username     VARCHAR(20) NOT NULL,
    password     VARCHAR(200) NOT NULL,
    firstname    VARCHAR(40),
    secondname   VARCHAR(40),
    email        VARCHAR(40)
);

ALTER TABLE users ADD CONSTRAINT users_pk PRIMARY KEY ( username );

ALTER TABLE amountingredients
    ADD CONSTRAINT amount_ingredients_fk FOREIGN KEY ( ingredients_name )
        REFERENCES ingredients ( name );

ALTER TABLE amountingredients
    ADD CONSTRAINT amount_recipes_fk FOREIGN KEY ( recipes_id )
        REFERENCES recipes ( id )
        on delete cascade;

ALTER TABLE comments
    ADD CONSTRAINT comments_recipes_fk FOREIGN KEY ( recipes_id )
        REFERENCES recipes ( id );

ALTER TABLE comments
    ADD CONSTRAINT comments_users_fk FOREIGN KEY ( users_username )
        REFERENCES users ( username );

ALTER TABLE photos
    ADD CONSTRAINT photos_recipes_fk FOREIGN KEY ( recipes_id )
        REFERENCES recipes ( id )
        on delete cascade;

ALTER TABLE recipes
    ADD CONSTRAINT recipes_category_fk FOREIGN KEY ( category_name )
        REFERENCES category ( name );

ALTER TABLE recipes
    ADD CONSTRAINT recipes_cuisine_fk FOREIGN KEY ( cuisine_name )
        REFERENCES cuisine ( name );


ALTER TABLE recipes
    ADD CONSTRAINT recipes_users_fk FOREIGN KEY ( users_username )
        REFERENCES users ( username );

ALTER TABLE steps
    ADD CONSTRAINT steps_recipes_fk FOREIGN KEY ( recipes_id )
        REFERENCES recipes ( id )
        on delete cascade;



ALTER TABLE recipesinrestaurant
    ADD CONSTRAINT relation_3_pk PRIMARY KEY ( restaurants_name,
                                               restaurants_city,
                                               recipes_id );
ALTER TABLE recipesinrestaurant
    ADD CONSTRAINT relation_3_recipes_fk FOREIGN KEY ( recipes_id )
        REFERENCES recipes ( id )
        on delete cascade;

ALTER TABLE recipesinrestaurant
    ADD CONSTRAINT relation_3_restaurants_fk FOREIGN KEY ( restaurants_name,
                                                           restaurants_city )
        REFERENCES restaurants ( name,
                                 city )
        on delete cascade;





