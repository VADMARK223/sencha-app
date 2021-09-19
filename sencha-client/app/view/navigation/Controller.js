/**
 * @author Markitanov Vadim
 * @since 05.09.2021
 */
Ext.define('Sencha.classic.view.navigation.Controller', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.navigation-controller',

    requires: [
        'Sencha.classic.view.pages.user.View',
        'Sencha.classic.view.pages.personnel.View'
    ],

    addTabs: function () {
        var self = this,
            tabPanel = self.lookup('navigation-view-tabpanel-ref');
        var activateTabId = 'user-page-view-id';
        // var activateTabId = 'personnel-page-view-id';

        tabPanel.add({
            id: 'user-page-view-id',
            title: 'Users',
            xtype: 'user-page-view'
        }, {
            id: 'personnel-page-view-id',
            title: 'Personnel',
            xtype: 'personnel-page-view'
        });

        tabPanel.setActiveTab(activateTabId);
    }
});