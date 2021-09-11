Ext.define('Sencha.classic.model.Personnel', {
    extend: 'Sencha.classic.model.Base',

    fields: [
        {
            name: 'id',
            type: 'number'
        },
        {
            name: 'email',
            type: 'string'
        },
        {
            name: 'phone',
            type: 'string'
        },
        {
            name: 'address',
            type: 'string'
        },
        {
            name: 'salary',
            type: 'number'
        }, {
            name: 'salaryValue',
            type: 'string',
            persist: false, // Не отсылать виртуальное поля на сервер
            depends: ['salary'],
            convert: function (value, record) {
                var salary = record.get('salary');
                if (Ext.isEmpty(salary) || salary === 0) {
                    return 'No salary.';
                }
                return Ext.String.format('{0} руб.', Ext.util.Format.number(salary, '0,000.00'));
            }
        },
        {
            name: 'firstname',
            type: 'string'
        },
        {
            name: 'lastname',
            type: 'string'
        },
        {
            name: 'patronymic',
            type: 'string'
        },
        {
            name: 'actual',
            type: 'boolean'
        },
        {
            name: 'position',
            type: 'string'
        }
    ],

    proxy: {
        reader: {
            type: 'json',
            rootProperty: 'results', // Название корневого узла для данных
            totalProperty: 'count' // Название поля в котором приходит общее кол-во записей для pagingtoolbar
        }
    }
});
