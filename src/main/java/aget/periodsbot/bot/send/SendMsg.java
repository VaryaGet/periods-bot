/*
 * MIT License
 *
 * Copyright (c) 2024 Artem Getmanskii
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package aget.periodsbot.bot.send;

import com.github.artemget.teleroute.send.Send;
import java.util.Objects;
import java.util.function.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public final class SendMsg implements Send<AbsSender> {
    private static final Logger log = LoggerFactory.getLogger(SendMsg.class);

    private final Supplier<SendMessage> tgSend;

    public SendMsg(final Update update, final String text) {
        this(new TextTgSend(update.getMessage().getFrom().getId().toString(), text));
    }

    public SendMsg(final String chatId, final String text) {
        this(new TextTgSend(chatId, text));
    }

    public SendMsg(final Supplier<SendMessage> tgSend) {
        this.tgSend = tgSend;
    }

    @Override
    public void send(final AbsSender send) {
        try {
            send.execute(this.tgSend.get());
        } catch (final TelegramApiException e) {
            log.error("Error sending message to chat: {}", this.tgSend.get().getChatId(), e);
        }
    }

    @Override
    public boolean equals(final Object object) {
        return this == object
            || object instanceof SendMsg && this.tgSend.equals(((SendMsg) object).tgSend);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.tgSend);
    }
}
