PGDMP          
             }            clash    16.4    16.4 
    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16399    clash    DATABASE     x   CREATE DATABASE clash WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Spanish_Spain.1252';
    DROP DATABASE clash;
                postgres    false            �            1259    16431    jugadors    TABLE     �   CREATE TABLE public.jugadors (
    id integer NOT NULL,
    nom character varying NOT NULL,
    nivell integer NOT NULL,
    copes integer NOT NULL,
    oro bigint NOT NULL,
    gemes bigint NOT NULL,
    estrelles bigint NOT NULL
);
    DROP TABLE public.jugadors;
       public         heap    postgres    false            �            1259    16424    partides    TABLE     �   CREATE TABLE public.partides (
    id integer NOT NULL,
    id_jug_1 integer NOT NULL,
    id_jug_2 integer NOT NULL,
    resultat character varying(3) NOT NULL,
    temps character varying(4) NOT NULL,
    tipus character varying NOT NULL
);
    DROP TABLE public.partides;
       public         heap    postgres    false            �          0    16431    jugadors 
   TABLE DATA           Q   COPY public.jugadors (id, nom, nivell, copes, oro, gemes, estrelles) FROM stdin;
    public          postgres    false    216   q
       �          0    16424    partides 
   TABLE DATA           R   COPY public.partides (id, id_jug_1, id_jug_2, resultat, temps, tipus) FROM stdin;
    public          postgres    false    215   �
       T           2606    16430    partides Partides_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.partides
    ADD CONSTRAINT "Partides_pkey" PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.partides DROP CONSTRAINT "Partides_pkey";
       public            postgres    false    215            V           2606    16437    jugadors jugadors_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.jugadors
    ADD CONSTRAINT jugadors_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.jugadors DROP CONSTRAINT jugadors_pkey;
       public            postgres    false    216            �   _   x�M�1
�0���]�[�V�DB����&Ͱ0�
f=uݮ��axfF0����q�!��� �����!���n=F�9k�SW����TII      �   �   x�M���0Dg�_�rvR���##�*!����s�QǼ<ݝ!&]�Uԭ��\�{Q�4�!��A��}����z�Ki4�ᕸ��DFD*��wd�V~��Ô��i��}�%��\�5���=4� �r؀�e`}��O��J)_~5�     