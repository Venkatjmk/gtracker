CREATE TABLE public.user_roles (
  role_id SERIAL,
  role_name character varying(15) NOT NULL,
  CONSTRAINT pk_ur_id PRIMARY KEY (role_id),
  CONSTRAINT uk_ur_name UNIQUE (role_name)
);

CREATE TABLE public.users (
  user_id SERIAL,
  created_time timestamp without time zone NOT NULL,
  email_id character varying(50) NOT NULL,
  first_name character varying(15) NOT NULL,
  last_modified_time timestamp without time zone NOT NULL,
  last_name character varying(15) NOT NULL,
  status character varying(15) NOT NULL,
  role_id integer NOT NULL,
  CONSTRAINT pk_users_id PRIMARY KEY (user_id),
  CONSTRAINT fk_users_roles_id FOREIGN KEY (role_id)
      REFERENCES public.user_roles (role_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT uk_users_eid UNIQUE (email_id)
);

CREATE TABLE public.goals (
  goal_id SERIAL,
  goal_type integer NOT NULL,
  goal_name character varying(30) NOT NULL,
  description character varying(100),
  status character varying(15) NOT NULL,
  user_id integer NOT NULL,
  created_by character varying(15) NOT NULL,
  created_time timestamp without time zone NOT NULL,
  last_modified_by character varying(15) NOT NULL,
  last_modified_time timestamp without time zone NOT NULL,
 
  CONSTRAINT pk_goals_id PRIMARY KEY (goal_id),
  CONSTRAINT fk_goals_user_id FOREIGN KEY (user_id)
      REFERENCES public.users (user_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE public.task_categories (
  category_id SERIAL,
  category character varying(15) NOT NULL,
  CONSTRAINT pk_tc_id PRIMARY KEY (category_id),
  CONSTRAINT uk_tc_category UNIQUE (category)
);

CREATE TABLE public.tasks (
  task_id SERIAL,
  created_by character varying(15) NOT NULL,
  created_time timestamp without time zone NOT NULL,
  dead_line timestamp without time zone NOT NULL,
  is_postponed boolean NOT NULL,
  modified_by character varying(15) NOT NULL,
  modified_time timestamp without time zone NOT NULL,
  one_time_task boolean NOT NULL,
  postponed_count integer NOT NULL,
  schedule character varying(25) NOT NULL,
  status character varying(15) NOT NULL,
  task_desc character varying(100) NOT NULL,
  task_type integer NOT NULL,
  goal_id integer,
  category_id integer NOT NULL,
  user_id integer,
  CONSTRAINT pk_tasks_id PRIMARY KEY (task_id),
  CONSTRAINT fk_tasks_category_id FOREIGN KEY (category_id)
      REFERENCES public.task_categories (category_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_tasks_goal_id FOREIGN KEY (goal_id)
      REFERENCES public.goals (goal_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_tasks_user_id FOREIGN KEY (user_id)
      REFERENCES public.users (user_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
