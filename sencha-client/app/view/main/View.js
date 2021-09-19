Ext.define('Sencha.classic.view.main.View', {
    extend: 'Ext.panel.Panel',
    viewModel: 'main-view-model',
    controller: 'main-controller',
    requires: [
        'Sencha.classic.view.navigation.View'
    ],

    keyMap: {
        SPACE: 'onKeyMapSpace'
    },

    layout: {
        type: 'vbox',
        align: 'stretch'
    },

    items: [{
        xtype: 'hbox-pane',
        defaults: {
            margin: 5
        },
        items: [{
            xtype: 'button',
            text: 'Sing out',
            handler: 'onSingOutHandler'
        }, {
            xtype: 'button',
            text: 'Clear cache',
            handler: 'onClearCacheHandler'
        }, {
            xtype: 'textfield',
            reference: 'token-textfield-ref',
            fieldLabel: 'Token',
            labelWidth: 40,
            readOnly: true,
            editable: false,
            selectOnFocus: true,
            bind: {
                value: '{token}'
            },
            listeners: {
                render: function () {
                    this.getEl().on('click', 'onTokenTextfieldClickHandler');
                }
            }
        }]
    }, {
        xtype: 'navigation-view'
    }]
});
