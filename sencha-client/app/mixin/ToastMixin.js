/**
 * @author Markitanov Vadim
 * @since 19.09.2021
 */
Ext.define('Sencha.classic.mixin.ToastMixin', {
    extend: 'Ext.Mixin',

    showToast: function (message) {
        Ext.toast({
            title: 'Info', // Заголовок всплывающего уведомления
            html: '<p>' + message + '</p>', // Сообщение всплывающего уведомления
            align: 'br', // Позиционирование всплывающего уведомления на окне (br - снизу и справа, t - сверху, r - справа, b - снизу, l - слева)
            iconCls: 'fa fa-info-circle'
        });
    },

    showToastError: function (message) {
        Ext.toast({
            title: 'Error', // Заголовок всплывающего уведомления
            html: '<p style="color:red">' + message + '</p>', // Сообщение всплывающего уведомления
            align: 'br', // Позиционирование всплывающего уведомления на окне (br - снизу и справа, t - сверху, r - справа, b - снизу, l - слева)
            iconCls: 'fa fa-exclamation-triangle'
        });
    }
});