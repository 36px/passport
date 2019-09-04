

function fire_ok(fn) {
    if (fn != null) {
        fn();
    }
}


export default {

    name: 'SignIn',

    namespaced: true,

    state: {
        ticket: ''
    },

    getters: {

    },

    mutations: {
        save_ticket(state, param) {
            state.ticket = param.ticket;
        }
    },

    actions: {

        step1(context, param) {

            var email = param.email;
            var method = 'post';
            var type = 'SignIn';
            var id = null;

            var content = {
                signIn: {
                    email
                }
            };

            var ok = res => {
                var ticket = res.content.signIn.ticket;
                context.commit('save_ticket', { ticket });
                fire_ok(param.ok);
            };

            context.dispatch('rest', { method, type, id, content, ok }, { root: true });

        },

        step2(context, param) {

            var verification = param.verification;
            var method = 'put';
            var type = 'SignIn';
            var id = context.state.ticket;

            var content = {
                signIn: {
                    verification,
                }
            };

            var ok = res => {
                fire_ok(param.ok);
            };

            context.dispatch('rest', { method, type, id, content, ok }, { root: true });

        },



    },

}
