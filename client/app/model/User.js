/**
 * Модель пользователя
 * @author Markitanov Vadim
 * @since 04.09.2021
 */
Ext.define('Sencha.classic.model.User', {
    extend: 'Sencha.classic.model.Base',

    requires: [
        'Sencha.classic.model.enum.Gender'
    ],

    fields: [
        {
            name: 'username', // Поле никнейма
            type: 'string',
            allowNull: false,
            defaultValue: 'Unknown' // Если от сервера не приходит поле, выставляется дефолтное значение
        }, {
            name: 'firstname', // Поле имени
            type: 'string',
            defaultValue: 'Unknown first name' // Если от сервера не приходит поле, выставляется дефолтное значение
        }, {
            name: 'lastname', // Поле фамилии
            type: 'string',
            defaultValue: 'Unknown last name' // Если от сервера не приходит поле, выставляется дефолтное значение
        }, {
            name: 'fullname', // Виртуальное поле фамилии и имя
            type: 'string',
            persist: false, // Не отсылать виртуальное поля на сервер
            depends: [
                'firstname',
                'lastname'
            ],
            calculate: function (data) {
                return Ext.String.format('{0} {1}', data.lastname, data.firstname);
            }
        }, {
            name: 'age', // Поля возраста пользователя
            type: 'int'
        }, {
            name: 'gender', // Пол пользователя
            type: 'string',
            defaultValue: GENDER.MALE.id
        }, {
            name: 'genderValue', // Значние пола пользователя
            type: 'string',
            depends: [
                'gender'
            ],
            calculate(data) {
                return data.gender === GENDER.MALE.id ? GENDER.MALE.value : GENDER.FEMALE.value;
            }
        }, {
            name: 'actual', // Признак актуальности пользователя
            type: 'boolean',
            defaultValue: true
        }
    ],

    hasMany: {
        model: 'Sencha.classic.model.Doc',
        name: 'docs',
        associationKey: 'docs',
        getterName: 'getDocs',
        setterName: 'setDocs'
    },

    validators: {
        gender: {
            type: 'inclusion',
            list: [
                'M',
                'F'
            ]
        }
    },

    proxy: {
        reader: {
            type: 'json',
            rootProperty: 'results'
        }
    }
});