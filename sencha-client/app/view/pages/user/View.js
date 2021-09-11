/**
 * @author Markitanov Vadim
 * @since 05.09.2021
 */
Ext.define('Sencha.classic.view.pages.user.View', {
    extend: 'Ext.panel.Panel',
    xtype: 'user-page-view',

    title: 'Users',

    controller: 'user-page-controller',
    viewModel: 'user-page-view-model',

    items: [
        {
            xtype: 'user-page-grid-view',
            reference: 'usersgrid-ref',
            height: 500,
            listeners: {
                selectionchange: 'onUsersGridSelectionChange'
            }
        },
        {
            xtype: 'user-page-docs-grid',
            reference: 'user-page-docs-grid-fer',
            height: 300,
            bind: {
                title: '{username ? username + "\'s documents" : "Choose user"}',
                store: '{userDocsStore}'
            }
        },
    ]
});