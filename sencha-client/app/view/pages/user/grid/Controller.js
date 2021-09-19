/**
 * @author Markitanov Vadim
 * @since 05.09.2021
 */
Ext.define('Sencha.classic.view.pages.user.grid.Controller', {
    extend: 'Ext.app.ViewController',

    alias: 'controller.user-page-grid-controller',

    requires: [
        'Ext.tip.ToolTip',
        'Sencha.classic.view.pages.user.grid.UsernameTooltip'
    ],

    listen: {
        component: {
            'user-page-grid-view': {
                afterrender: 'onUserPageGridViewAfterRender'
            }
        }
    },

    onUserPageGridViewAfterRender: function (self) {
        var view = self.getView();
        view.tip = Ext.create('Ext.tip.ToolTip', {
            target: self.el, // Html который должен монтироваться
            delegate: (view.cellSelector + ' .fa'), // Элемент над которым срабатывает отображение всплывающей подсказки
            cls: 'tooltip-default',
            maxWidth: 320, // Максимальная ширина всплывающего окна
            anchor: 'left',
            defaultAlign: 'l-c',
            listeners: {
                beforeshow: function (me) {
                    me.removeAll();

                    var target = me.triggerElement;

                    if (target.classList.contains('fa')) {
                        var data = view.getRecord(target.parentNode).getData(); // Забираем данные с записи для прокидки в шаблон

                        if (target.classList.contains('isAdmin')) { // В классах иконки ищем нужный
                            data.tooltipText = 'Admin'; // Устанавливаем текст всплывающего окна
                            me.update(Ext.create('Sencha.classic.view.pages.user.grid.UsernameTooltip').apply(data)); // Обновляем контент всплывающего окна
                            return;
                        }

                        if (target.classList.contains('actualUser')) { // В классах иконки ищем нужный
                            data.tooltipText = 'Actual user'; // Устанавливаем текст всплывающего окна
                            me.update(Ext.create('Sencha.classic.view.pages.user.grid.UsernameTooltip').apply(data)); // Обновляем контент всплывающего окна
                            return;
                        }

                        if (target.classList.contains('notActualUser')) { // В классах иконки ищем нужный
                            data.tooltipText = 'Not actual user'; // Устанавливаем текст всплывающего окна
                            me.update(Ext.create('Sencha.classic.view.pages.user.grid.UsernameTooltip').apply(data));  // Обновляем контент всплывающего окна
                            return;
                        }
                    }

                    return false;
                },
                destroy: function () {
                    delete view.tip; // Удаление подсказки после уничтожения представления
                }
            }
        });
    }
});