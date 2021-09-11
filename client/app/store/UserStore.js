/**
 * Хранилище пользователей
 * @author Markitanov Vadim
 * @since 05.09.2021
 */
Ext.define('Sencha.classic.store.UserStore', {
    extend: 'Ext.data.Store',
    alias: 'store.user-store',
    storeId: 'user-store-id', // По storeId в отладочной консоли можно посмотреть загруженное содержимое Ext.getStore('user-store-id').getData().items, более длинная запись Ext.StoreManager.lookup('user-store-id').getData().items
    requires: [
        'Sencha.classic.model.User'
    ],
    model: 'Sencha.classic.model.User', // Модель хранилища

    autoLoad: true, // Автоматическая загрузка
    autoSync: false, // Автоматическая синхронизации Store с его Proxy, после каждого редактирования одной из его записей.
    sorters: [{
        property: 'actual',
        direction: 'DESC'
    }, {
        property: 'age',
        direction: 'ASC'
    }],
    listeners:{
        load: function (self, records, successful, operation) {
            console.debug('operation', operation);
        }
    }
});