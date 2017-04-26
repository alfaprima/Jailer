SELECT
     T.TABNAME AS TABLENAME, 
     C.COLNAME,
     C.TYPENAME,
     CASE WHEN C.TYPENAME in (''VARCHAR'', ''CHARACTER'', ''DECIMAL'') THEN C.LENGTH ELSE 0 END AS LENGTH,
     SCALE,
     C.COLNO
FROM syscat.TABLES T JOIN syscat.COLUMNS C ON T.TABNAME=C.TABNAME AND C.TABSCHEMA=T.TABSCHEMA
WHERE T.TABSCHEMA=UPPER(''{0}'') AND T.TABNAME=''{1}'' AND T.TYPE=''T'' 
ORDER BY TABLENAME, C.COLNO