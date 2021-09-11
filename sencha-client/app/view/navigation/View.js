/**
 * Представление навигации
 * @author Markitanov Vadim
 * @since 05.09.2021
 */
Ext.define('Sencha.classic.view.navigation.View', {
    extend: 'Ext.container.Container',
    xtype: 'navigation-view',

    controller: 'navigation-controller',

    initComponent: function () {
        this.callParent(arguments);
        this.getController().addTabs();
    },

    items: [{
        xtype: 'tabpanel',
        reference: 'navigation-view-tabpanel-ref'
    }]
});