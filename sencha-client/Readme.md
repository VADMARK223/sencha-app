# Оптимизация
 - Избегать `render` у колонок в `grid`, лучше делать через виртуальные поля модели. 
 
# Памятки
 - При поиске по отфильтрованным записям, использовать такой набор параметров: Ext.getStore(`<storeId>`).findRecord(`<fieldName>`, `<value>`, 0, false, false, true)
 - Ext.getCmp('<component-id>') взятие компонента по его ID, только в консоли.
