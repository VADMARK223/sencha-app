/**
 * @author Markitanov Vadim
 * @since 04.09.2021
 */
Ext.define('Sencha.classic.view.pages.user.grid.View', {
    extend: 'Ext.grid.Panel',
    xtype: 'user-page-grid-view',
    requires: [
        'Sencha.classic.view.pages.user.grid.ViewModel'
    ],

    viewModel: 'user-page-grid-view-model',
    controller: 'user-page-grid-controller',

    title: 'Users',

    bind: '{usersStore}',

    columns: {
        defaults: {
            resizable: true, // Настйрока разрешения изменения размеров колонок
            draggable: false // Настройка разрешения перетаскивания колонок
        },
        items: [{
            text: 'Username',
            dataIndex: 'username',
            xtype: 'templatecolumn',
            tpl: Ext.create('Sencha.classic.view.pages.user.grid.UsernameTpl'),
            flex: 1
            // tdCls:
        }, {
            text: 'Roles',
            dataIndex: 'rolesValues'
        }, {
            text: 'Is admin',
            dataIndex: 'isAdmin'
        }, {
            text: 'First name',
            dataIndex: 'firstname',
            flex: 1,
            hidden: true
        }, {
            text: 'Last name',
            dataIndex: 'lastname',
            flex: 1,
            hidden: true
        }, {
            text: 'Full name',
            dataIndex: 'fullname',
            flex: 2
        }, {
            text: 'Gender',
            dataIndex: 'genderValue',
            flex: 1
        }, {
            text: 'Actual',
            dataIndex: 'actual',
            flex: 1
        }, {
            text: 'Age',
            dataIndex: 'age',
            flex: 1
        }]
    },

    listeners: {
        viewready: function (grid) {
            var usersStore = grid.getViewModel().getStore('usersStore');
            usersStore.on('load', function () {
                grid.getSelectionModel().select(0);
            });
        },
        afterrender: function (grid) {
            grid.getView().getRowClass = function (record) {
                if (record.get('actual')) {
                    return 'x-grid-row-green';
                } else {
                    return 'x-grid-row-gray';
                }
            };
        }
    }
});