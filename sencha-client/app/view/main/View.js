Ext.define('Sencha.classic.view.main.View', {
    extend: 'Ext.panel.Panel',
    viewModel: 'main-view-model',
    controller: 'main-controller',
    requires: [
        'Sencha.classic.view.navigation.View'
    ],

    // Альтернатива создания отдельного класса контроллера
    /*controller: {
        <functions>
    },*/

    keyMap: {
        SPACE: 'onKeyMapSpace'
    },

    layout: {
        type: 'vbox',
        align: 'stretch'
    },

    items: [{
        xtype: 'navigation-view'
    }]
});
