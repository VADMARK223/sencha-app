/**
 * Хранилище персонала
 * @author Markitanov Vadim
 * @since 05.09.2021
 */
Ext.define('Sencha.classic.store.PersonnelStore', {
    extend: 'Ext.data.Store',
    alias: 'store.personnel-store',
    storeId: 'personnel-store-id', // По storeId в отладочной консоли можно посмотреть загруженное содержимое Ext.getStore('personnel-store-id').getData().items, , более длинная запись Ext.StoreManager.lookup('personnel-store-id').getData().items
    requires: [
        'Sencha.classic.model.Personnel'
    ],
    model: 'Sencha.classic.model.Personnel', // Модель хранилища

    autoLoad: true, // Автоматическая загрузка
    autoSync: true, // Автоматическая синхронизации Store с его Proxy, после каждого редактирования одной из его записей.

    pageSize: 5, // Настройка для параметра limit, который уходит на сервер

    remoteSort: true, // Массив sorters будет уходить на сервер строковым параметром sort
    sorters: [{
        property: 'id',
        direction: 'ASC'
    }]
});