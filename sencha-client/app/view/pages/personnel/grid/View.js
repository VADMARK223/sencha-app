/**
 * @author Markitanov Vadim
 * @since 05.09.2021
 */
Ext.define('Sencha.classic.view.pages.personnel.grid.View', {
    extend: 'Ext.grid.Panel',
    xtype: 'personnel-grid-view',
    requires: [
        'Sencha.classic.view.pages.personnel.grid.ViewModel'
    ],
    viewModel: 'personnelGrid-view-model',

    title: 'Personnel',

    height: 300,

    bind: '{personnelStore}',

    columns: {
        defaults: {
            resizable: false, // Настйрока разрешения изменения размеров колонок
            draggable: false // Настройка разрешения перетаскивания колонок
        },
        items: [{
            text: 'Id',
            dataIndex: 'id',
            flex: 1,
            editor: 'textfield'
        }, {
            text: 'Last name',
            dataIndex: 'lastname',
            flex: 2,
            editor: 'textfield'
        }, {
            text: 'First name',
            dataIndex: 'firstname',
            flex: 2,
            editor: 'textfield'
        }, {
            text: 'Patronymic',
            dataIndex: 'patronymic',
            flex: 2,
            editor: 'textfield'
        }, {
            text: 'Email',
            dataIndex: 'email',
            flex: 1,
            editor: 'textfield'
        }, {
            text: 'Position',
            dataIndex: 'position',
            flex: 1,
            editor: 'textfield'
        }, {
            text: 'Phone',
            dataIndex: 'phone',
            flex: 1,
            editor: 'textfield'
        }, {
            text: 'Address',
            dataIndex: 'address',
            flex: 1,
            editor: 'textfield',
            hidden: true
        }, {
            text: 'Actual',
            dataIndex: 'actual',
            flex: 1
        }, {
            text: 'Salary',
            dataIndex: 'salaryValue',
            flex: 1,
            editor: 'textfield',
            hidden: true
        }]
    },

    bbar: {
        xtype: 'pagingtoolbar',
        displayInfo: true, // Обображать дополнительную информацию
        displayMsg: 'Displaying {0} to {1} of {2} records.', // Шаблон для дополнительной информации
        emptyMsg: 'Personnel table is empty.' // Отображаемый текст, если не записей для отображения
    },

    selModel: 'rowmodel',
    plugins: {
        ptype: 'rowediting',
        clicksToEdit: 2 // Количество кликов для начала редактирования
    },

    listeners: {
        viewready: function (grid) {
            var vm = grid.getViewModel(),
                store = vm.getStore('personnelStore');
            store.on('load', function () {
                grid.getSelectionModel().select(0);
            });
        }
    }
});