
/* Drop Tables */

DROP TABLE IF EXISTS complaint_item;




/* Create Tables */

CREATE TABLE complaint_item
(
	-- The primary key
	id int NOT NULL,
	-- Discript how angry you are.
	anger_level int,
	-- Your nick name
	nick_name text,
	-- Time when you post this message
	post_time timestamp,
	-- The title of this post. (optional)
	title text,
	-- The content of this post.
	post_content text,
	-- PERSON or AFFAIR
	content_type text,
	user_id varchar(20),
	PRIMARY KEY (id)
) WITHOUT OIDS;



/* Comments */

COMMENT ON COLUMN complaint_item.id IS 'The primary key';
COMMENT ON COLUMN complaint_item.anger_level IS 'Discript how angry you are.';
COMMENT ON COLUMN complaint_item.nick_name IS 'Your nick name';
COMMENT ON COLUMN complaint_item.post_time IS 'Time when you post this message';
COMMENT ON COLUMN complaint_item.title IS 'The title of this post. (optional)';
COMMENT ON COLUMN complaint_item.post_content IS 'The content of this post.';
COMMENT ON COLUMN complaint_item.content_type IS 'PERSON or AFFAIR';



