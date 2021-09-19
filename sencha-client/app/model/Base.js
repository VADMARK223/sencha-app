Ext.define('Sencha.classic.model.Base', {
    extend: 'Ext.data.Model', // Модель или сущность представляют собой некоторый объект, которым управляет ваше приложение

    requires: [
        'Ext.data.identifier.Uuid', // Этот класс генерирует UUID в соответствии с RFC 4122. Этот класс имеет свойство id по умолчанию. Это означает, что используется один экземпляр, если свойство id не переопределено.
        'Sencha.classic.util.AppConstants'
    ],

    fields: [{
        name: 'id',
        type: 'auto',
        critical: true // Помечаются поля, которые всегда нужно отправлять на сервер, даже если они не изменилось.
    }],

    idProperty: 'id', // Не обязательно, по умлчинию применяется к полю 'id'
    identifier: 'uuid', // Генерацию уникального uuid для поля idProperty

    schema: {
        namespace: 'Sencha.classic.model',
        urlPrefix: Constants.urlPrefix,
        proxy: {
            type: 'ajax', // Можно не указывать, тип ajax по умолчанию
            url: '{prefix}/{entityName:uncapitalize}', // prefix задается в свойстве urlPrefix, entityName - название модели (uncapitalize убирает заглавные буквы с первой буквы данной строки)

            headers: {
                'Authorization': 'Bearer ' + sessionStorage.getItem(Constants.userToken)
            },

            listeners: {
                exception: function (proxy, response) {
                    var msg;
                    if (!Ext.isEmpty(response.responseJson) && !Ext.isEmpty(response.responseJson.err)) {
                        msg = Ext.String.format('{0}', response.responseJson.err);
                    } else {
                        msg = Ext.String.format('Error: {0}. See server logs.', response.status);
                    }
                    Ext.MessageBox.show({
                        title: 'Server error',
                        msg: msg,
                        icon: Ext.MessageBox.ERROR,
                        buttons: Ext.Msg.OK
                    });
                }
            }
        }
    }
});
