/**
 * @author Markitanov Vadim
 * @since 04.09.2021
 */
Ext.define('Sencha.classic.view.pages.user.docsGrid.View', {
    extend: 'Ext.grid.Panel',
    xtype: 'user-page-docs-grid',
    requires: [
        'Sencha.classic.view.pages.user.docsGrid.ViewModel'
    ],

    viewModel: 'user-page-docs-grid-view-model',
    emptyText: 'Document list is empty.',

    title: 'User documents',

    columns: {
        defaults: {
            resizable: true, // Настйрока разрешения изменения размеров колонок
            draggable: false // Настройка разрешения перетаскивания колонок
        },
        items: [{
            text: 'Name',
            dataIndex: 'name',
            editor: 'textfield',
            flex: 1
        }, {
            text: 'Size',
            dataIndex: 'sizeValue',
            editor: 'textfield',
            flex: 1
        }]
    }
});