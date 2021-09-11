Ext.application({
    extend: 'Sencha.classic.Application',

    name: 'Sencha.classic',

    requires: [
        // Это автоматически загрузит все классы в пространстве имен Sencha.app,
        // чтобы классы приложений не требовали друг друга.
        'Sencha.classic.*'
    ]
});
